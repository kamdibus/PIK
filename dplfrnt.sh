#! /bin/bash
front_dst_dir='front';
front_src_dir='src/frontend';

echo "removing old dir..."
rm -fr ../$front_dst_dir
echo "creating new dir..." 
mkdir ../$front_dst_dir 
echo "copying files..."
cp -r $front_src_dir/* ../$front_dst_dir 
cd ../$front_dst_dir 
echo "initializing git repo..."
git init
echo "commiting changes..."
git add .
git commit -m "deploy"
echo "adding remote..."
heroku git:remote -a wtxgs
echo "deploying..." 
git push -f heroku master
echo "cleaning..."
cd ..
rm -fr $front_dst_dir
exit 0