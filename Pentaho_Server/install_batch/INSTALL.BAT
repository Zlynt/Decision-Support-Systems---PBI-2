title PBI2 Installer
cls
echo A instalar...
xcopy /y C:\sad\implementacao\Pentaho_Server\changed\jsp\PBI2.jsp C:\sad\Pentaho\pentaho-server\tomcat\webapps\pentaho\jsp

xcopy /y C:\sad\Pentaho\pentaho-server\tomcat\webapps\pentaho\WEB-INF\web.xml C:\sad\implementacao\Pentaho_Server\original\WEB-INF\
xcopy /y C:\sad\implementacao\Pentaho_Server\changed\WEB-INF\web.xml C:\sad\Pentaho\pentaho-server\tomcat\webapps\pentaho\WEB-INF

xcopy /y C:\sad\implementacao\Pentaho_Server\changed\lib\PBI2.jar C:\sad\Pentaho\pentaho-server\tomcat\webapps\pentaho\WEB-INF\lib
echo Instalado!
PAUSE