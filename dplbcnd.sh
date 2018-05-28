#! /bin/bash
set -e
app_name=$(find . -name "app*.jar")
echo "web: java -jar $app_name --spring.profiles.active=heroku" > Procfile
heroku deploy:jar $app_name -a wtxgsb