# BInvestment Shares Management System
BInvestment is a spring boot web application used to keep track of the ownership of shares of any company. Users can see the shares they own and are able to sell and purchase shares. The user must have £10,000 in funds as a minimum to be able to buy shares. 

## Web Application Architecture
BInvestment was created using a microservices architecture. This was type of application architecture was chosen to separate the application features and to make it easier to update and maintain different features of the application without affecting the whole application. The web application consumes information from three API's: Shares-Service, User-Investment-Service and Users-Service. 

- The Shares-service microservice is responsible for accessing all shares that available for users to invest in. Also, this micorservice connects to the shares database. 

- The User-Investment-service microservice is responsible for keeping track of all the investments each user has. It also connects to the investments database. 

- The Users-Service microservice is responsible for keeping track of all the users and their personal information. This microservice connects to the users database. 

##Database Architecture

MySQL was used as the database for this project. Three tables were created: 
1. Shares table
2. Investments table
3. Users table

The shares table contains the following information about each share: shareId, name of the share and the share unit price(the price for one share).
The users table contains the following user information: userId, company name, phone number, email, balance and password.
The investments table contains the following information about the investments each user has made: userId, shareId, share name and share quantity. The userId and shareId are foreign keys and were made this way to link the user with their investments in this table and to also link the information about a share to the share information in the investments table. 

All the information in these three individal microservices is used in the main MVC application, BInvestment-Investment-MVC. An MVC pattern was used for the main spring application to separate the front-end from the backend of the application. 

___
## Design Concept For User Interface & User Experience 
Once the web application is running, the user starts on the welcome page where they can choose between signing up or signing into their account using their UserId and password. If the user needs to sign up they will need to create a UserId, password, input their company name, phone number and e-mail. Once signed up, they will need to sign in. The user must have £10,000 in funds as a minimum to be able to buy shares. 

Once the user is signed into their account they will be taken to the menu page where they can choose from five options: 
1. Deposit Funds 
2. Buy Shares 
3. Sell Shares
4. View All Shares
5. Log Out

The designs for the pages the user interacts with are shown below. 

### Welcome Page: 

![Welcome-to-Bright-Investment](https://user-images.githubusercontent.com/75650155/229965053-e78f6a27-54fa-4f78-9092-7663b62caffd.png)

### Sign In Page: 

![Sign-in-page](https://github.com/ihenwk/BInvestment/blob/8a3c8adca992b6c7f96bffac3170549fcf1a7d07/User-Interface-Mock-Up-Design%20/Login.png)


### Sign Up Page:

![Sign-up](https://github.com/ihenwk/BInvestment/blob/f01987b8648d1644bfdfb05b8abc813f04b01f0c/User-Interface-Mock-Up-Design%20/Sign-Up%20.png)


### Menu Page: 
The user is able to choose between five options once logged into their account: 
1. Deposit Funds 
2. Buy Shares 
3. Sell Shares
4. View All Shares
5. Log Out

![Menu](https://github.com/ihenwk/BInvestment/blob/f01987b8648d1644bfdfb05b8abc813f04b01f0c/User-Interface-Mock-Up-Design%20/Main-Menu.png)


### View All Shares Page:

![View All Shares](https://github.com/ihenwk/BInvestment/blob/f01987b8648d1644bfdfb05b8abc813f04b01f0c/User-Interface-Mock-Up-Design%20/View-All-Investments.png)

### Buy Shares Page:

![Buy Shares](https://github.com/ihenwk/BInvestment/blob/f01987b8648d1644bfdfb05b8abc813f04b01f0c/User-Interface-Mock-Up-Design%20/Buy-Share.png)

### Buy Shares Success Page: 

![Buy Shares Success](https://github.com/ihenwk/BInvestment/blob/f01987b8648d1644bfdfb05b8abc813f04b01f0c/User-Interface-Mock-Up-Design%20/Buy-Share%20-%20success-message.png)


### Sell Shares Page:

![Sell Shares](https://github.com/ihenwk/BInvestment/blob/f01987b8648d1644bfdfb05b8abc813f04b01f0c/User-Interface-Mock-Up-Design%20/SellShare.png)

### Sell Shares Success Page:

![Sell Shares Success](https://github.com/ihenwk/BInvestment/blob/f01987b8648d1644bfdfb05b8abc813f04b01f0c/User-Interface-Mock-Up-Design%20/Sell-Share%20-%20success-message.png)

### Deposit Funds Page:

![Deposit Funds](https://github.com/ihenwk/BInvestment/blob/f01987b8648d1644bfdfb05b8abc813f04b01f0c/User-Interface-Mock-Up-Design%20/Deposit-Funds.png)


___

## How to run the web application
1. Open SQL scripts and run the shares table, investments table and users table. 
2. Start the Users-service API 
3. Start the User-Investments-Service API
4. Start the Shares-Service API 
5. Finally start the main spring application Bright Investment MVC. This will appear on port: 8084.

