package edu.uw.ajz.concrete.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;

import edu.uw.ajz.concrete.account.ConcreteAccountFactory;
import edu.uw.ajz.concrete.account.ConcreteAddress;
import edu.uw.ajz.concrete.account.ConcreteCreditCard;
import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountFactory;
import edu.uw.ext.framework.account.Address;
import edu.uw.ext.framework.account.CreditCard;
import edu.uw.ext.framework.dao.AccountDao;

public class ConcreteAccountDao implements AccountDao {

	
	@Override
	public void close() throws AccountException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount(String arg0) throws AccountException {
		
		File acctFile = new File ("target/accounts/"+arg0);
		
		   String[] children = acctFile.list(); 
		   for (int i=0; i<children.length; i++)
		   { 
		     File file = new File(acctFile, children[i]); 
		     file.delete();
		   }
		   acctFile.delete(); 
		
	}

	@Override
	public Account getAccount(String arg0) {
		File acctFile = new File ("target/accounts/"+arg0+"/acct.dat");
		if(!acctFile.exists()) return null;
		
		try (DataInputStream in = new DataInputStream(new FileInputStream(acctFile)))
		{	
			String name = in.readUTF();
			int pwLength = in.readInt();
			byte[] pw = new byte[pwLength];
			in.readFully(pw);
			int bal = in.readInt();

			AccountFactory af = new ConcreteAccountFactory();
			Account acct = af.newAccount(name, pw, bal);

			String fullname = in.readUTF();
			if(fullname.length() > 0) acct.setFullName(fullname);
			
			String email = in.readUTF();
			if(email.length() > 0) acct.setEmail(email);
			
			String phone = in.readUTF();
			if(phone.length() > 0) acct.setPhone(phone);
			
			
			File addrFile = new File("target/accounts/"+arg0+"/addr.dat");
			if(addrFile.exists()) 
			{
				try(BufferedReader brAddr = new BufferedReader(new FileReader(addrFile)))
				{
					Address addr = new ConcreteAddress();
					
					String city = brAddr.readLine();
					if(city.length() > 0) addr.setCity(city);
					
					String state = brAddr.readLine();
					if(state.length() > 0) addr.setState(state);
					
					String street = brAddr.readLine();
					if(street.length() > 0) addr.setStreetAddress(street);
							
					String zip = brAddr.readLine();
					if(zip.length() > 0) addr.setZipCode(zip);
					
					acct.setAddress(addr);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			File ccFile = new File("target/accounts/"+arg0+"/card.dat");
			if(ccFile.exists()) 
			{
				try(BufferedReader brCc = new BufferedReader(new FileReader(ccFile)))
				{
					CreditCard cc = new ConcreteCreditCard();
					
					String acctNum = brCc.readLine();
					if(acctNum.length() > 0) cc.setAccountNumber(acctNum);
					
					String expDt = brCc.readLine();
					if(expDt.length() > 0) cc.setExpirationDate(expDt);
					
					String holder = brCc.readLine();
					if(holder.length() > 0) cc.setHolder(holder);
					
					String issuer = brCc.readLine();
					if(issuer.length() > 0) cc.setIssuer(issuer);
					
					String type = brCc.readLine();
					if(type.length() > 0) cc.setType(type);
					
					acct.setCreditCard(cc);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			
			return acct;
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void reset() throws AccountException {

		try
		{
			File acctFile = new File("target/accounts");
	
			String[] children = acctFile.list(); 
			for (int i=0; i<children.length; i++)
			{
				this.deleteAccount(children[i]);
			} 
		   
		   acctFile.delete(); 
		} catch (NullPointerException ne)
		{
			//throw new AccountException("No accounts exist");
		}
		
	}

	@Override
	public void setAccount(Account arg0) throws AccountException 
	{
		String acctName = arg0.getName();
		File acctDir = new File("target/accounts/"+acctName);
		
		if (acctDir.exists()) this.deleteAccount(acctName);
		acctDir.mkdirs(); 
		
		String acctDirStr = acctDir.getAbsolutePath();
		

		File acctFile = new File(acctDirStr + "/acct.dat");
		
		try (DataOutputStream os = new DataOutputStream(new FileOutputStream(acctFile)))
		{

			if(!acctFile.exists()) acctFile.createNewFile();
			os.writeUTF(arg0.getName());
			os.writeInt(arg0.getPasswordHash().length);
			os.write(arg0.getPasswordHash());
			os.writeInt(arg0.getBalance());
			
			String fullname = (arg0.getFullName()!=null) ? arg0.getFullName() : "";
				os.writeUTF(fullname);
			String email = (arg0.getEmail()!=null) ? arg0.getEmail() : "";
				os.writeUTF(email);
			String phone = (arg0.getPhone()!=null) ? arg0.getPhone() : "";
				os.writeUTF(phone);
		}
		catch(IOException ioe)
		{
			ioe.printStackTrace();
		}
			
			Address addr = arg0.getAddress();
			if(addr != null)
			{
				File addrFile = new File(acctDirStr + "/addr.dat");
				try(BufferedWriter brAddr = new BufferedWriter(new FileWriter(addrFile)))
				{

					if(!addrFile.exists()) addrFile.createNewFile();
					
					String city = (addr.getCity()!=null) ? addr.getCity() : "";
					brAddr.write(city); brAddr.newLine();
					
					String state = (addr.getState()!=null) ? addr.getState() : "";
					brAddr.write(state); brAddr.newLine();
					
					String street = (addr.getStreetAddress()!=null) ? addr.getStreetAddress() : "";
					brAddr.write(street); brAddr.newLine();
							
					String zip = (addr.getZipCode()!=null) ? addr.getZipCode() : "";
					brAddr.write(zip); brAddr.newLine();
				}
				catch(IOException ioe)
				{
					ioe.printStackTrace();
				}	
			}
			
			
			CreditCard cc = arg0.getCreditCard();
			if(cc!=null)
			{
				File ccFile = new File(acctDirStr + "/card.dat");
				try (BufferedWriter brCC = new BufferedWriter(new FileWriter(ccFile)))
				{
					if(!ccFile.exists()) ccFile.createNewFile();

					String acctNum = (cc.getAccountNumber()!=null) ? cc.getAccountNumber() : "";
					brCC.write(acctNum); brCC.newLine();
					
					String expDt = (cc.getExpirationDate()!=null) ? cc.getExpirationDate() : "";
					brCC.write(expDt); brCC.newLine();
					
					String holder = (cc.getHolder()!=null) ? cc.getHolder() : "";
					brCC.write(holder); brCC.newLine();
					
					String issuer = (cc.getIssuer()!=null) ? cc.getIssuer() : "";
					brCC.write(issuer); brCC.newLine();
					
					String type = (cc.getType()!=null) ? cc.getType() : "";
					brCC.write(type); brCC.newLine();
					
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
			}
			
		
	}

}
