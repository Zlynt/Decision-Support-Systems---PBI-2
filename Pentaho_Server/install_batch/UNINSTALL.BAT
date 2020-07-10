title PBI2 Uninstaller
cls
echo A desinstalar...
del C:\sad\Pentaho\pentaho-server\tomcat\webapps\pentaho\jsp\PBI2.jsp
xcopy /y C:\sad\implementacao\Pentaho_Server\original\WEB-INF\web.xml C:\sad\Pentaho\pentaho-server\tomcat\webapps\pentaho\WEB-INF

del C:\sad\Pentaho\pentaho-server\tomcat\webapps\pentaho\WEB-INF\lib\PBI2.jar


echo Desinstalado!
PAUSE