#####This fork of Swing application, gives you an impression, which technical possabilities you have, when you migrate Swing to JavaFX. You find more details and a tutorial in the screencast below.#####

If you have further questions or migration support requests, just give me a ping (alexander.casall@saxsys.de)

Video Screencast:

<a href="http://youtu.be/z--ue6QSkCA"><img src="https://developers.google.com/youtube/images/YouTube-icon-full_color.png" alt="alt text" width="300""></a>


Original Description:

# twitter4j-swing-sample

A desktop client for Twitter.

## Intention

The goal is to write a desktop client that includes all functionalities the **Twitter API v1.1** offers.  
Furthermore it should look as pretty as it's possible using **Java Swing**. ;)

## How to login with your Twitter account
You have to generate your own OAuth tokens on [https://dev.twitter.com](https://dev.twitter.com "developers' page").

1. log in
2. click on *My applications* in the drop down menu under your icon
3. click on *Create New App* complete the form
4. After that click on your new created application
5. at tab **Permissions** choose *Read, Write and Access direct messages* and confirm
6. at tab **API Keys** click on *generate my access token*

Now you can start your client and insert the four strings into the Login dialog.

## Technical Details
+ **Library:** Twitter4J v4.0.2
+ **Database:** SQLite (driver: sqlite-jdbc-3.7.2.jar)
