const { readPrediction } = require("./prediction.service");
const axios = require("axios");

module.exports = {
    readPrediction: (req,res)=>{
        const flaskUrl = 'https://teras-model-l3azxq25lq-et.a.run.app/prediction';

        axios.get(flaskUrl)
            .then(response =>{
                res.send(response.data);
            })
            .catch(error =>{
                res.status(500).send('Error calling Flask: ' + error);
            });
    },
};