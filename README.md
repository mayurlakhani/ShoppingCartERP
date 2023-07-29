# ShoppingCartERP
SpringBoot + Mysql  Rest Apis
### 1. http://localhost:8081/shoppingCart/
curl --location 'http://localhost:8081/shoppingCart/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "username": "j",
    "password":"jji",
    "email": "may@gmail.com"
}'

### 2. http://localhost:8081/shoppingCart/profile?username=j
curl --location --request POST 'http://localhost:8081/shoppingCart/profile?username=j' \
--header 'Content-Type: application/json' \
--data '{
    "descr":"hi",
    "number":"8989",
    "gender": "male"

}'

### 3. http://localhost:8081/shoppingCart/account?id=1
curl --location --request POST 'http://localhost:8081/shoppingCart/account?id=1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "iban":"DE23iu98r223iu2",
    "bic":"DEUTCH",
    "accountHolderName":"mayur",
    "bankName": "DE",
    "password": "Mayur@108199"
}'
