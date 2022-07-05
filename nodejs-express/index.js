var { faker } = require('@faker-js/faker')
var express = require('express')
var promBundle = require('express-prom-bundle')
var spdy = require('spdy')
var fs = require('fs')

// Add the options to the prometheus middleware most option are for http_request_duration_seconds histogram metric
const metricsMiddleware = promBundle({
    includeMethod: true,
    includePath: true,
    includeStatusCode: true,
    includeUp: true,
    customLabels: { project_name: 'nodejs-express' },
    promClient: {
        collectDefaultMetrics: {
        }
    }
})

// utility
function createRandomPerson() {
    return {
        firstName: faker.name.firstName(),
        lastName: faker.name.lastName(),
        cellphone: faker.phone.phoneNumber(),
        nickname: faker.random.words(2),
        favoriteBeer: faker.random.words(3),
        animal: faker.animal.dog(),
        jobTitle: faker.name.jobTitle(),
        company: faker.company.companyName(),
        favoriteDish: faker.random.words(2),
        birthday: faker.date.birthdate(),
        bio: faker.lorem.words(50),
        univeristy: faker.random.words(3),
    }
}

// express
const app = express()
app.use(metricsMiddleware)

// endpoints
const num_elements = 100
app.get('/ppl', (req, res) => {
    res.send(Array.apply(null, { length: num_elements })
        .map(Function.call, createRandomPerson)
    )
})

// spdy
const port = 8443
const options = {
    key: fs.readFileSync("./server.key"),
    cert: fs.readFileSync("./server.crt")
}
spdy.createServer(options, app)
    .listen(port, (err) => {
        if (err) {
            throw new Error(err)
        }
        console.log(`App listening on port ${port}`)
    })
