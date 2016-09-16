# ViewCollection
**a view assemble in my project.**
1. `HeartSwipeRefreshLayout`(a heart view SwipeRefreshLayout).</br>
2. `HeartFlowerView`(a follower flow effect).</br>
3. `SideBar`(a letter sort from sidebar).</br>
4. `CleanDialog`(a simple dialog in my application).</br>

## How to use
### Maven
**Add the JitPack repository to your build file**
```DSL
<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```
**Add the dependency**
```DSL
<dependency>
	    <groupId>com.github.jiangTaoQuite</groupId>
	    <artifactId>ViewCollection</artifactId>
	    <version>0.0.1_alpha</version>
	</dependency>
```
### Gradle

**Add it in your root build.gradle at the end of repositories:**
```DSL
allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
```
**Add the dependency**
```DSL
dependencies {
	        compile 'com.github.jiangTaoQuite:ViewCollection:0.0.1_alpha'
	}
```

## License

Copyright 2016 jiangTaoQuite

Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.

