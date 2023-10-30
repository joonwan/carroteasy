import {useState} from "react";
import * as ImagePicker from "expo-image-picker";
import {Button, Image, StyleSheet, Text, TextInput, View} from "react-native";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";

const getMemberId = async () =>{
    try{
        const memberId= await AsyncStorage.getItem("memberId");
        return memberId;
    }catch(e){
        console.log(e);
    }
}

const ItemRegisterPage = ({navigation}) =>{

    const [itemName, onChangeName] = useState("");
    const [price, onChangePrice] = useState("");
    const [content, onChangeContent] = useState("");
    const [image, setImage] = useState(null);
    const pickImage = async () =>{
        let result = await ImagePicker.launchImageLibraryAsync({
                mediaTypes:ImagePicker.MediaTypeOptions.All,
                allowsEditing:true,
                quality:1
            }
        );

        console.log("image = " + result);
        if(!result.canceled){
            setImage(result.assets[0].uri);
        }

    }
    return (
        <View>
            <View style={{marginVertical:10,
                marginHorizontal:10
            }}>
                <View>

                    <Button title="add your image" onPress={pickImage}/>
                    {image && <Image source={{uri : image}} style = {{width: 100, height: 100 }}/>}
                </View>


                <Text style={
                    styles.text
                }>제목
                </Text>
                <TextInput
                    autoCapitalize="none"
                    placeholder="제목을 입력하세요"
                    keyboardType="web-search"
                    style={styles.input}
                    onChangeText={onChangeName}
                    value={itemName}/>

                <Text style={
                    styles.text
                }>
                    가격
                </Text>
                <TextInput
                    autoCapitalize="none"
                    placeholder="가격을 입력하세요"
                    style={styles.input}
                    onChangeText={onChangePrice}
                    value={price}
                    keyboardType="numeric"
                />
                <Text style={
                    styles.text
                }>
                    내용
                </Text>
                <TextInput
                    autoCapitalize="none"
                    placeholder="내용을 입력하세요"
                    style = {styles.input}
                    onChangeText={onChangeContent}
                    value = {content}
                />
            </View>



            <Button title="Create Item" onPress={ async ()=>{
                const formData = new FormData();
                const memberId = await getMemberId();
                console.log(memberId);
                formData.append('memberId', memberId);
                formData.append('itemName', itemName);
                formData.append('price', price);
                formData.append('content', content);

                if(image !== null){
                    formData.append('image', {
                        uri:image,
                        name:'image.png',
                        type:'image/png'
                    });
                }

                const boundary = `---------------------------${Math.random().toString(16).substr(2, 16)}`;


                axios.post("http://localhost:8080/items/new",
                    formData,{
                        headers: {
                            'Content-Type': `multipart/form-data; boundary=${boundary}`
                        }
                    }
                ).then(()=>{
                    onChangeContent("");
                    onChangePrice("");
                    onChangeName("");
                    setImage(null);
                    navigation.navigate("홈")
                }).catch((error) => console.log(error));



            }}/>
        </View>
    );
};

const styles = StyleSheet.create({

    input :{
        height:40,
        margin:12,
        borderWidth:1,


    }

})

export default ItemRegisterPage;
