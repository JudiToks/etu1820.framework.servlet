javac -d . Mapping.java
javac -d . Urls.java
javac -d . Utility.java
javac -cp /opt/tomcat/webapps/sprint1/WEB-INF/lib/jakarta.servlet-api-6.0.0.jar *.java
jar cvf FrameWork.jar ./
cp FrameWork.jar ../../testframework/WEB-INF/lib/