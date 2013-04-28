LiME Creative Labs
=================

<a href="https://play.google.com/store/apps/details?id=com.limecreativelabs.app">
  <img alt="Android app on Google Play"
       src="https://developer.android.com/images/brand/en_app_rgb_wo_45.png" />
</a>

LiME is an Android App based on its [homonymous blog][5]. It was created to show live demos of tutorials created on the blog.

This App is basically a visual quick helper to detect tutorials you may be interested in and easily find its implementation. Main Activity contains a link to the [blog][5] and to this page. Each tutorial also has a link to its written version.

LiME Creative Labs blog is written in Spanish, but is easy to understand using a tool like Google Translate. If you don't like this option, source code is well documented an organized. A package has been created for each tutorial, so the code is isolated.

This App will be updated on every new tutorial added. What you can currently find:

* __Action Bar Refresh__: when the user clicks the Refresh Button, an indeterminate <tt>ProgressBar</tt> is shown until the operation is performed.

* __Action Bar Search__: A search action lets the user search for something by showing a textbox on Action Bar.

* __Multiple selection in ListView using ActionBarSherlock__: Implementation of multiple selection using a SelectionAdapter.

![Example Image][1]

Building the project
-------------------------

It is recommended to use maven to compile this project. Download the project and import to your IDE as a maven project. You will need to add [listviewanimations][2] jar to you local maven repository. If you are on a Unix shell, just run the provided `installDependencies.sh` script.

If you want to use it as a regular Android project, go to the dependencies section and download those projects as libraries or jars to the build path.

Dependencies
--------------------

* [ActionBarSherlock][3] by Jake Wharton.
* [Gson][4]
* [ListViewAnimations][2] by Niek Haarman

Developed By
--------------------

Antonio Leiva Gordillo - <antonioleivag@gmail.com>

<a href="http://twitter.com/lime_cl">
  <img alt="Follow me on Twitter"
       src="https://raw.github.com/antoniolg/LimeApp/master/art/social/twitter.jpg" />
</a>
<a href="https://plus.google.com/107221928564556085738">
  <img alt="Follow me on Google+"
       src="https://raw.github.com/antoniolg/LimeApp/master/art/social/google-plus.jpg" />
</a>
<a href="http://es.linkedin.com/in/antoniolg">
  <img alt="Follow me on LinkedIn"
       src="https://raw.github.com/antoniolg/LimeApp/master/art/social/linked-in.jpg" />

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
 [3]: https://www.actionbarsherlock.com
 [4]: https://code.google.com/p/google-gson/
 [5]: http://www.limecreativelabs.com/?utm_source=main&utm_medium=readme&utm_campaign=github