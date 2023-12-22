# Te-Ras - Cloud Computing

## Base URL
Te-Ras API Endpoint :
`https://teras-backend-l3azxq25lq-et.a.run.app/`

## GCP Services Used in this project :
1. Cloud Run        : To Deploy API application build with Express Js
2. Cloud SQL        : We used Cloud SQL MYSQL instances to store our database
3. Cloud Storage    : To store static files like images

## API Endpoint Route
**User Registration**
```
Method : POST
Route  : /register
Body   :
{
        "name" : "name",
	"email" : "email@gmail.com",
	"address": "address",
	"password":"password"
}
```

**Login**
```
   Method  : POST
   Route   : /login
   Body    :
   {
	"email":"email@gmail.com",
	"password" : "password"
   }
```

**Get Prediction Data**
```
   Method   : GET
   Route    : /prediction
```
   
