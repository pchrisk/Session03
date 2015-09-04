<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0" 
          xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
  <xsl:output method="text" />
  <!-- Either of theses work
  <xsl:variable name='newline'><xsl:text>
</xsl:text></xsl:variable>
  -->
  <!-- Prefere this one... -->
  <xsl:variable name='newline'><xsl:text>&#10;</xsl:text></xsl:variable>
  <xsl:template match="/">
     <xsl:apply-templates/>
  </xsl:template>

  <xsl:template match="quote">
    <xsl:apply-templates select="symbol"/> Currently Trading at <xsl:apply-templates select="price"/>
    <xsl:value-of select="$newline"/>

  </xsl:template>

</xsl:stylesheet> 
