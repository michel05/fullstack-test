# GoBotsBack

Esse projeto disponibiliza uma API com Basic Authentication que irá disponibilizar as informações das reconmendações de músicas.

## Development server api

Run `start.sh` ou `gradle bootRun` para iniciar a aplicação.

## API Endpoint

```json
curl --location --request GET 'http://localhost:8080/recommendations?city=goiania' \
--header 'Authorization: Basic YWRtaW46YWRtaW4='
```