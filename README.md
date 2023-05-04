# Java Connection to Elasticache(Redis) with AUTH Enabled

1. Check java and javac available.

```
java --version
javac --version
```

2. Download Jedis version 3.9.0 from https://jar-download.com/artifacts/redis.clients/jedis?p=2. Note: starting from version 3.10, JedisShardInfo is removed. https://stackoverflow.com/questions/72187735/class-file-for-redis-clients-jedis-jedisshardinfo-not-found
3. Unzip it into `lib` folder.

```
unzip jar_files.zip
mkdir lib
mv *.jar lib
```

4. Compile `hello.java` file.

```
javac -cp "lib/*" -d . -Xlint:deprecation hello.java 
```

5. Run the `hello.class`

```
java -cp ".:lib/*" hello
```

6. Output
```
Hello, World!
Connected to Redis
```