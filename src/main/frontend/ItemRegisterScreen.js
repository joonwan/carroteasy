import {useState} from "react";
import * as ImagePicker from "expo-image-picker";
import {Button, Image, StyleSheet, Text, TextInput, View} from "react-native";
import axios from "axios";

const ItemRegisterPage = ({navigation}) =>{

    const [itemName, onChangeName] = useState("제목을 입력하세요");
    const [price, onChangePrice] = useState("가격을 입력하세요");
    const [content, onChangeContent] = useState("내용을 입력하세요");
    const memberId = 1;
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
                    style = {styles.input}
                    onChangeText={onChangeContent}
                    value = {content}
                />
            </View>



            <Button title="Create Item" onPress={()=>{
                const formData = new FormData();
                formData.append('memberId', memberId);
                formData.append('itemName', itemName);
                formData.append('price', price);
                formData.append('content', content);
                formData.append('image', {
                    uri:image,
                    name:'image.png',
                    type:'image/png'
                });
                const boundary = `---------------------------${Math.random().toString(16).substr(2, 16)}`;


                axios.post("http://localhost:8080/items/new",
                    formData,{
                        headers: {
                            'Content-Type': `multipart/form-data; boundary=${boundary}`
                        }
                    }
                ).then(()=>{
                    onChangeName("제목을 입력하세요");
                    onChangePrice("가격을 입력하세요");
                    onChangeContent("내용을 입력하세요");
                    console.log("success");
                    console.log(itemName);
                    console.log(price);
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
