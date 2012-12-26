# twitter4j-swing-sample #

A desktop client for Twitter.

## Intention ##

The goal is to write a desktop client that includes all functionalities the **Twitter API v1.1** offers.  
Furthermore it should look as pretty as it's possible using Java Swing.

## Login ##

### Where to enter my Password? ###
Insert your credentials (consumer key, consumer secret, access token, access token secret) into the 'login.xml' file.  
You can insert credentials of multiple accounts and then choose them at runtime and switch between them **[not yet implemented]**.

### Where to look for them? ###
You generate them on the [Developers site of Twitter] (https://dev.twitter.com "The information site of Twitter for developers").  
<ol>
  <li>log in</li>
  <li>create a new application (see "My Applications")</li>
  <li>set the access to "Read, Write and Access direct messages" (don't forget to save your settings!)</li>
  <li>[re]create your access token and insert all the four strings into the 'login.xml' file</li>
</ol>

## Technical Details ##

**Programming Language:** Java  
**GUI library used:** Swing  
**Framework used:** Twitter4J 3.0.3
