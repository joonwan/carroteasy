import {FlatList, Image, RefreshControl, StyleSheet, Text, TouchableOpacity, View} from "react-native";
import {useCallback, useEffect, useState} from "react";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";

const getMemberId = async () =>{
    try{
        const memberId = await AsyncStorage.getItem("memberId");
        return memberId;
    }catch (e){
        console.log(e);
    }
}

const InterestItemsScreen = ({navigation}) => {

    const [items, setItems] = useState(null);
    const [refreshing, setRefreshing] = useState(false);
    const [memberId, setMemberId] = useState("");

    const onRefresh = useCallback(async ()=>{
        setRefreshing(true);
        const memberId = await getMemberId();
        console.log(memberId);
        setMemberId(memberId);
        const url = "http://localhost:8080/members/" + memberId + "/interestItems";
        console.log(url);
        axios.get(url)
            .then(result => setItems(result.data))
            .catch(err => console.log(err));
        setTimeout(() => {
            setRefreshing(false);
        }, 2000);

    },[]);

    useEffect(async ()=>{
        const memberId = await getMemberId();
        console.log(memberId);
        setMemberId(memberId);
        const url = "http://localhost:8080/members/" + memberId + "/interestItems";
        const call = async () => {
            axios.get(url)
                .then(result => setItems(result.data))
                .catch(err => console.log(err));
        }
        call();
    },[]);
    return (
       <FlatList data={items}
                 refreshControl={<RefreshControl refreshing={refreshing} onRefresh={onRefresh}/>
                                }
                 keyExtractor={(item) => item.itemId}
                 renderItem={({item}) =>
                     <TouchableOpacity
                         onPress={() => navigation.navigate("ItemDetailScreen", {
                             itemId: item.itemId,
                             memberId: memberId
                         })}
                         style={styles.list}>

                         <View style={styles.itemBox}>
                             <View style={styles.imgSection}>
                                 <Image style={styles.img}
                                        source={{uri: "http://localhost:8080/items/image/" + item.uri}}/>
                             </View>
                             <View style={styles.textSection}>
                                 <Text style={styles.itemName}>{item.itemName}</Text>
                                 <Text>{item.city}{item.gu} {item.dong}</Text>
                                 <Text>{item.createDate}</Text>
                                 <Text style={styles.itemPrice}>{item.price} 원</Text>
                                 <Text style={styles.interestCount}> 관심수 : {item.interestCount}</Text>
                             </View>
                         </View>


                     </TouchableOpacity>
       }

           />
    );
}

const styles= StyleSheet.create({

    container: {
        marginVertical: 20
    },

    list: {
        marginHorizontal: 20,
        marginVertical: 10,
        paddingVertical: 10,
        borderBottomWidth: 1,
    },
    itemBox: {
        flexDirection: "row"
    },
    itemName: {
        fontSize: 20,
        padding: 2
    },

    itemPrice: {
        fontSize: 20,
        padding: 2,
        fontWeight: "bold"
    },
    imgSection: {
        flex: 1,
        marginRight: 40
    },
    textSection: {
        flex: 3
    },
    interestCount: {
        textAlign: "right"
    },
    img :{
        width: 110,
        height: 110,
        borderRadius:10,
    }

})
export default InterestItemsScreen;