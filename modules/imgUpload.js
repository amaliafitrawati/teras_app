'use strict'
const {Storage} = require('@google-cloud/storage');
const fs = require('fs');
const path = require('path');

const pathKey = path.resolve('./serviceaccountkey.json')

// TODO: Sesuaikan konfigurasi Storage
const gcs = new Storage({
    projectId: 'coba-coba-project-407404',
    keyFilename: pathKey
})

// TODO: Tambahkan nama bucket yang digunakan
const bucketName = 'teras_forum_images'
const bucket = gcs.bucket(bucketName)

function getPublicUrl(filename) {
    return 'https://storage.googleapis.com/' + bucketName + '/' + filename;
}

let ImgUpload = {}

ImgUpload.uploadToGcs = (req, res, next) => {
    if (!req.file) return next()

    const gcsname = Date.now();
    const file = bucket.file(gcsname)

    const stream = file.createWriteStream({
        metadata: {
            contentType: req.file.mimetype
        }
    })

    stream.on('error', (err) => {
        req.file.cloudStorageError = err
        next(err)
    })

    stream.on('finish', () => {
        req.file.cloudStorageObject = gcsname
        req.file.cloudStoragePublicUrl = getPublicUrl(gcsname)
        next()
    })

    stream.end(req.file.buffer)
}

module.exports = ImgUpload