LiME Creative Labs
=================

LiME is an Android App based on its homonym blog. It was created to show live demos of tutoriales created on the blog.

This App is basically a visual quick helper to detect tutorials you may be interested in and easily find its implementation.

Main Activity contains a link to the blog and to this page. Each tutorial also has a link to its written version.

LiME Creative Labs blog is written in Spanish, but is easy to understand using a tool like Google Translate. If you don't like this option, source code is well documented an organized. A package has been created for each tutorial, so the code is isolated.

This App will be updated on every new tutorial added. What you can currently find:

* Action Bar Refresh: when the user clicks the Refresh Button, an indeterminate <tt>ProgressBar</tt> is shown until the operation is performed.
* Action Bar Search: A search action lets the user search for something by showing a textbox on Action Bar.

![Example Image][1]

Find the application at Google Play Store:

<a href="https://play.google.com/store/apps/details?id=com.limecreativelabs.app">
  <img alt="Android app on Google Play"
       src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>


Building the project
-------------------------

It is recommended to use maven to compile this project. Download the project and import to your IDE as a maven project. You will need to add [listviewanimations][2] jar to you local maven repository.

If you want to use it as a regular Android project, go to the dependencies section and download those projects as libraries or jars to the build path.

License
-----------

    Copyright 2013 Antonio Leiva

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.




 [1]: https://raw.github.com/antoniolg/LimeApp/master/art/screenshots.png
 [2]: https://github.com/nhaarman/ListViewAnimations