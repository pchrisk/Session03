<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" 
          xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="text" />

  <xsl:variable name='newline' select="'&#10;'"/>
  <xsl:variable name='leftbrace'><xsl:text>&#123;</xsl:text></xsl:variable>
  <xsl:variable name='rightbrace'><xsl:text>&#125;</xsl:text></xsl:variable>
  <xsl:variable name='comma'><xsl:text>&#44;</xsl:text></xsl:variable>
  <xsl:variable name='colon'><xsl:text>&#58;</xsl:text></xsl:variable>

  <xsl:template match="/">
     <xsl:apply-templates/>
  </xsl:template>

  <xsl:template match="quote">
    <xsl:value-of select="$leftbrace"/>
    <xsl:text>&quot;symbol&quot;</xsl:text>
    <xsl:value-of select="$colon"/>
    <xsl:text>&quot;</xsl:text>
	<xsl:apply-templates select="symbol"/>
    <xsl:text>&quot;</xsl:text>
	<xsl:value-of select="$comma"/>
	
    <xsl:text>&quot;price&quot;</xsl:text>
    <xsl:value-of select="$colon"/>
	<xsl:apply-templates select="price"/>
    <xsl:value-of select="$rightbrace"/>
    <xsl:value-of select="$newline"/>

  </xsl:template>

</xsl:stylesheet> 