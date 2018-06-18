<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:a="com/namespace/xsd/school.xsd"
                xmlns:ad="com/namespace/xsd/address.xsd"
                xmlns:st="com/namespace/xsd/student.xsd">

    <xsl:template match="/">
        <html>
            <body>
                <h2>My School info</h2>
                <p>
                   School address is: <xsl:value-of select="a:school/ad:address"/>
                </p>
                <p>School students are:</p>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>FirstName</th>
                        <th>LastName</th>
                        <th>Patronymic</th>
                    </tr>
                    <xsl:for-each select="a:school/a:students/st:student">
                        <tr>
                            <td><xsl:value-of select="st:firstName"/></td>
                            <td><xsl:value-of select="st:lastName"/></td>
                            <td><xsl:value-of select="st:patronymic"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>