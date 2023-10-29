import {useState} from "react";
import {Button, TextInput, View, Text, StyleSheet} from "react-native";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";

const LoginScreen = ({navigation}) => {

    const [loginId, setLoginId] = useState(null);
    const [password, setPassword] = useState(null);
    const [result, setResult] = useState(null);

    const storeMemberId = async (value) =>{
        try{
            const memberId = JSON.stringify(value);
            await AsyncStorage.setItem("memberId",memberId );
        }catch(e){
            console.log(e);
        }
    }

    const readData = async() =>{
        try{
            const value = await AsyncStorage.getItem("memberId");
            if(value !== null){
                console.log(value);
            }
        }catch(e){
            console.log(e);
        }
    }
    return (
        <View style={styles.container}>
            <Text
                style={{
                    margin: 12,
                    fontSize: 20,
                    fontWeight: "bold"
                }}
            >아이디</Text>
            <TextInput
                autoCapitalize="none"
                style={styles.input}
                onChangeText={setLoginId}
                value={loginId}/>
            <Text
                style={{
                    margin: 12,
                    fontSize: 20,
                    fontWeight: "bold"
                }}
            >비밀번호</Text>
            <TextInput
                autoCapitalize="none"
                style={styles.input}
                onChangeText={setPassword}
                value={password}/>
            <View style={styles.buttonBox}>
                <Button
                    title="로그인"
                    onPress={
                        () => {
                            const data = {loginId, password};
                            const json = JSON.stringify(data);

                            axios.post(
                                "http://localhost:8080/login",
                                json,
                                {
                                    headers: {
                                        'Content-Type': 'application/json'
                                    }
                                }
                            ).then(
                                response => {
                                    console.log(response.data);
                                    const memberId = response.data.memberId;
                                    storeMemberId(memberId);
                                    if(response.data.memberId != null){
                                        navigation.navigate("MyTabs")
                                    }
                                }
                            ).catch(err => console.log(err));
                        }
                    }
                />

                <Button
                    title="회원 가입"
                    onPress={
                        () => {
                            navigation.navigate("MemberRegisterScreen");
                        }
                    }
                />
            </View>

        </View>
    )
}

const styles = StyleSheet.create({

    container : {
        marginVertical:100,

        alignContent : "center"
    },
    input: {
        height: 40,
        margin: 12,
        borderWidth: 1,
    },
    buttonBox:{
        flexDirection : "row",
        justifyContent : "center"
    }


})
export default LoginScreen;