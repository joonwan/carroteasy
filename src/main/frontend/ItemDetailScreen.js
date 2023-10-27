import {useEffect, useState} from "react";
import axios from "axios";
import {Button, Image, SafeAreaView, ScrollView, Text, View, StyleSheet} from "react-native";
import {createBottomTabNavigator} from "@react-navigation/bottom-tabs";

const Tab = createBottomTabNavigator();


function Item({navigation, route}) {

    const {itemId, memberId} = route.params;
    const [item, setItem] = useState(null);
    const url = "http://localhost:8080/items/" + itemId;
    const [like, setLike] = useState(false);

    useEffect(() => {
        const getResponse = async () => {
            const response = await axios.get(url);
            setItem(response.data);
        };
        getResponse();
    }, []);

    if (item == null) {
        return <Text>로딩중</Text>
    }

    return (
        <SafeAreaView style={{flex: 1}}>
            <ScrollView style={styles.scrollSection}>

                <View>
                    <Image style={styles.img} source={{uri: "http://localhost:8080/items/image/" + item.uri}}/>
                </View>

                <View style={styles.sellerSection}>
                    <View style={{flex: 7}}>
                        <Text style={styles.sellerName}>{item.sellerName}</Text>
                        <Text>{item.gu} {item.dong}</Text>
                    </View>
                    <View style={{flex: 2}}>
                        <Text style={styles.mannerTemperature}>{item.mannerTemperature}</Text>
                    </View>
                </View>

                <View style={styles.itemSection}>
                    <Text style={styles.itemName}>
                        {item.itemName}
                    </Text>
                    <Text style={{marginVertical: 10}}>
                        {item.createDate}
                    </Text>
                    <Text>
                        {item.content}
                        {item.content}
                        {item.content}
                        {item.content}
                        {item.content}
                        {item.content}
                        {item.content}
                        {item.content}
                        {item.content}
                        {item.content}
                        {item.content}
                        {item.content}
                        {item.content}
                        {item.content}
                        {item.content}
                        {item.content}
                    </Text>
                </View>
            </ScrollView>
            <View style={styles.footer}>
                <View style={styles.likeButton}>
                    <Button title="like" onPress={() => console.log("like")}/>

                </View>
                <View style={styles.priceSection}>
                    <Text style={styles.price}>{item.price}원</Text>
                </View>
                <View style={styles.chatButton}>
                    <Button title="chat" onPress={() => console.log("chat")}/>
                </View>
            </View>
        </SafeAreaView>
    );
}

const styles = StyleSheet.create({
    img: {
        width: 430,
        height: 430,
    },
    sellerSection: {
        padding: 20,
        flexDirection: "row",
        borderBottomWidth: 1,
    },
    sellerName: {
        fontSize: 20,
        marginBottom: 10
    },
    mannerTemperature: {
        fontSize: 20
    },
    itemSection: {
        margin: 20
    },
    itemName: {
        fontSize: 30,
        fontWeight: "bold"
    },
    scrollSection: {
        flexGrow: 4
    },
    footer: {
        flexGrow: 2,
        flexDirection: "row",
        padding: 20
    },
    likeButton: {
        flex: 1,
    },
    priceSection: {
        flex: 2,
        justifyContent: "center",

    },
    price: {
        fontWeight: "bold"
    },
    chatButton: {
        flex: 2,
    }
})
export default Item;