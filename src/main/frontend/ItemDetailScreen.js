import {useEffect, useState} from "react";
import axios from "axios";
import {Button, Image, SafeAreaView, ScrollView, Text, View, StyleSheet} from "react-native";
import {createBottomTabNavigator} from "@react-navigation/bottom-tabs";
import AsyncStorage from "@react-native-async-storage/async-storage";

const Tab = createBottomTabNavigator();
const getMemberId = async ()=>{
    try{
        const memberId = await AsyncStorage.getItem("memberId");
        return memberId;
    }catch(err){
        console.log(err);
    }
}
function Item({navigation, route}) {

    const {itemId, id} = route.params;
    const [item, setItem] = useState(null);
    const url = "http://localhost:8080/items/" + itemId;
    useEffect(() => {
        const getResponse = async () => {
            const memberId = await getMemberId();
            console.log(memberId);
            const response = await axios.get(url,{
                params :{
                    "memberId" : memberId
                }
            });
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
                    <Text>{item.uri}</Text>
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
                    <Button title={item.like.toString()} onPress={
                        async () => {
                            console.log("hello woorld");
                            const memberId = await getMemberId();
                            const data = {memberId};
                            const jsonData = JSON.stringify(data);
                            console.log(jsonData);
                            axios.put("http://localhost:8080/items/" + itemId,
                                jsonData,{
                                params: {"memberId":memberId}
                                }
                            ).then(console.log("success"))
                                .catch(e => console.log(e));
                        }}/>

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