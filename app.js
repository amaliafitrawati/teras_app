require("dotenv").config();
const axios = require('axios');
const express = require('express');
const app = express();
const predictionRouter = require("./api/prediction/prediction.router");
const userRouter = require("./api/users/user.router");
const forumRouter = require("./api/forums/forum.router");

app.use(express.json());

app.use("/prediction", predictionRouter);
app.use("/forum", forumRouter);
app.use("/", userRouter);



const port = process.env.APP_PORT || 8080;
app.listen(port, () => {
    console.log(`server is up and running on : ${port}`);
});