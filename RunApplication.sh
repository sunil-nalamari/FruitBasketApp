
FILE=$1
if [ -f "$FILE" ]; then
    echo "$FILE exists."
else
    echo "Error Code 404."
    echo "$FILE does not exist."
    exit
fi

mvn clean install
java -cp target/CodingChallange-FruitBasket-1.0-SNAPSHOT.jar FruitBasket $1