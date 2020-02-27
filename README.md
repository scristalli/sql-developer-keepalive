# Keepalive extension for SQL Developer #

## What is this?
You know when you are working in SQL Developer, and you have to reconnect because your connection has been terminated?
Pretty annoying, right?  
This extension solves your problem! By sending a simple query to the database every _n_ seconds, it keeps your connection alive.

## How do I install it?
Just download the latest release: ![GitHub release (latest by date)](https://img.shields.io/github/v/release/scristalli/sql-developer-keepalive)  
It comes as a ZIP, which you can simply install in SQL Developer with the menu _Help > Check for Updates_.  
After restarting SQL developer, click the first icon from the right to start using the keepalive.
![keepalive icon](https://raw.githubusercontent.com/scristalli/sql-developer-keepalive/master/keepalive-icon.png)  
Something is not clear? Read two sections ahead ;)

## Why did you make this?
The community wanted this. I started from [MinChen Chai's extension for SQL Developer 3](https://sites.google.com/site/keepaliveext/) and ported it to the OSGi framework used from version 4.

## Can I contribute?
Sure!
* Something doesn't work? Please open an [issue](https://github.com/scristalli/sql-developer-keepalive/issues).
* Want to document something better? Please edit the [wiki](https://github.com/scristalli/sql-developer-keepalive/wiki).
* Want to improve the extension? Please open a [pull request](https://github.com/scristalli/sql-developer-keepalive/compare).
* Want to say thanks or to help me develop more software? Read the next section ;)

## Is your software free?
It is, and released under the [MIT license](https://github.com/scristalli/sql-developer-keepalive/blob/master/LICENSE).  
However, it would be nice to have an incentive to do other little projects. For years I had a small dedicated website with ads, and the total yield was 0 as I couldn't even reach the threshold for getting a payment.  
So enough with ads, just pay me a beer* if my extension has saved you some time! ![Beerpay](https://img.shields.io/beerpay/scristalli/sql-developer-keepalive)  
Help me proving that a project with ![GitHub All Releases](https://img.shields.io/github/downloads/scristalli/sql-developer-keepalive/total) deserves more than $0!




*_use of the funds for purchase and consumption of alcoholic beverages is not guaranteed_
