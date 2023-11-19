nohup java -jar /srv/gendo-calendar/gendo-calendar-0.0.1-SNAPSHOT.jar > /srv/gendo-calendar/log.txt 2>&1 &
echo $! > /srv/gendo-calendar/pid.file