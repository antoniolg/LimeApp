curl -O https://github.com/nhaarman/ListViewAnimations/blob/master/com.haarman.listviewanimations-1.9.jar

mvn install:install-file -Dfile=./com.haarman.listviewanimations-1.9.jar -DgroupId=com.haarman.listviewanimations -DartifactId=ListViewAnimations -Dversion=1.9 -Dpackaging=jar

rm com.haarman.listviewanimations-1.9.jar 
