<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" 
          xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:template match="/">
    <html>
      <head>
        <title>Stock Quote</title>
      </head>
      <body>
        <xsl:apply-templates/>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="quote">
  <b>
    <xsl:apply-templates select="symbol"/>
	</b> Currently Trading at 
    <xsl:apply-templates select="price"/>
  </xsl:template>

</xsl:stylesheet> 

