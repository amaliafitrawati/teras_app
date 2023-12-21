const { createPool } = require("mysql");

const pool = createPool({
    host : ,
    port: '3306',
    user : 'root',
    password : '',
    database :'',
    connectionLimit:10,
});

module.exports = pool;