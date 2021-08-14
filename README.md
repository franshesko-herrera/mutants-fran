# mutants-fran

### URL (se encuentra alojado en AWS)
La url donde se encuentran desplegados los servicios es:
~~~
http://mutants-env.eba-emiyajmx.us-east-1.elasticbeanstalk.com/
~~~
### SERVICIO POST DNA:
Envía un arreglo de Strings con bases de ADN parta verificar si es un mutante o no
#### endpoint :  
~~~
[POST] http://mutants-env.eba-emiyajmx.us-east-1.elasticbeanstalk.com/meli/challenge/mutant
~~~
#### Body:
~~~
{
    "dna" : ["aaaagg", "aaaagc", "tcaccc", "aggcaa", "ggtctt", "ggttga"]
}
~~~
#### posibles respuestas:
Respuesta para un mutante:
~~~
{
    "code": 200,
    "message": "mutant"
}
~~~
Respuesta para un humano:
~~~
{
    "code": 403,
    "message": "human"
}
~~~
Respuesta si la matriz no es cuadrada, o si el array de Strings no tiene mínimo 4 entradas, o si alguna letra no está permitida (A,C,G,T) 
~~~
{
    "code": 409,
    "message": "Data Error. (Matrix size / ADN nitro bases)"
}
~~~
Respuesta si intentamos grabar otro resultado para el mismo ADN:
~~~
{
    "code": 409,
    "message": "could not execute statement; SQL [n/a]; constraint [\"PUBLIC.UKQ5BREIYUF4UUHINS4618PO0IS_INDEX_1 ON PUBLIC.DNA(DNA) VALUES 12\"; SQL statement:\ninsert into dna (id, dna, mutant) values (null, ?, ?) [23505-200]]; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"
}
~~~

### SERVICIO GET STATS
Hace el conteo de cuantos mutantes hay, cuantos humanos hay registrados, y la relación entre estas dos cifras.
#### endpoint :
~~~
[GET] http://mutants-env.eba-emiyajmx.us-east-1.elasticbeanstalk.com/meli/challenge/stats
~~~
#### respuesta :
~~~
{
    "countMutantDna": 5,
    "countHumanDna": 6,
    "ratio": 0.8333333134651184
}
~~~

### SERVICIO GET DNA/ALL (Adicional)
Muestra una lista completa de estudios de ADN registrados en la base de datos
#### endpoint :
~~~
[GET] http://mutants-env.eba-emiyajmx.us-east-1.elasticbeanstalk.com/meli/challenge/dna/all
~~~
#### respuesta :
~~~
    {
        "id": 1,
        "dna": "AAAAGG,AATAGC,TCACCC,AGGCAA,GGTCTT,GGTCTC",
        "mutant": true
    },
    {
        "id": 2,
        "dna": "AAAAGG,AATAGC,TCACCC,AGGCAA,GGTCTT,GGTATC",
        "mutant": false
    },
    ...
    ...
    ...
    {
        "id": n,
        "dna": "AAAAGG,AATAGC,TCACCC,AGGCAA,GGTCTT,GGTCTA",
        "mutant": true
    }
~~~

