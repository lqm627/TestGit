nohup java -jar -Djava.security.egd=file:/dev/./urandom jms-wechatapi-20170125-SNAPSHOT8014.jar --server.port=8014 --management.port=7014 --logging.config=./config/8014logback-spring.xml >/dev/null 2>error &

