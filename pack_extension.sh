cd $(dirname $0)
cd "SQL Developer 4 keepalive"

if [ -f deploy/keepalive.jar ]
    then
        mkdir deploy/META-INF
        cp ../META-INF/bundle.xml deploy/META-INF/bundle.xml
        cd deploy
        zip keepalive.zip -r *
        rm -r META-INF
    else
        echo "keepalive.jar is missing"
fi 
