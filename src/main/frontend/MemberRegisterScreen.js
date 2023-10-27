import {useState} from "react";
import {Button, StyleSheet, Text, TextInput, View} from "react-native";

const MemberRegisterScreen = ({navigation}) =>{

    const [loginId, setLoginId] = useState(null);
    const [password, setPassword] = useState(null);
    const [name, setName] = useState(null);
    const [city, setCity] = useState(null);
    const [gu, setGu] = useState(null);
    const [dong, setDong] = useState(null);



    return (
        <View style={styles.container}>

            <Text style={styles.text}>
                ID
            </Text>

            <TextInput
                style={styles.input}
                placeholder="아이디를 입력해주세요"
                onChangeText={setLoginId}
                value={loginId}
            />

            <Text style={styles.text}>
                Password
            </Text>

            <TextInput
                style={styles.input}
                placeholder="패스워드를 입력해 주세요"
                onChangeText={setPassword}
                value={password}
            />

            <Text style={styles.text}>
                Name
            </Text>

            <TextInput
                style={styles.input}
                placeholder="이름을 입력해주세요"
                onChangeText={setName}
                value={name}
            />

            <Text style={styles.text}>
                City
            </Text>

            <TextInput
                style={styles.input}
                placeholder="도시를 입력해주세요"
                onChangeText={setCity}
                value={city}
            />

            <Text style={styles.text}>
                Goo
            </Text>

            <TextInput
                style={styles.input}
                placeholder="구를 입력해주세요"
                onChangeText={setGu}
                value={gu}
            />

            <Text style={styles.text}>
                Dong
            </Text>

            <TextInput
                style={styles.input}
                placeholder="동을 입력해주세요"
                onChangeText={setDong}
                value={dong}
            />

            <Button title="회원 가입" onPress={()=>console.log("ok")}/>
            <Button title="이전" onPress={()=> navigation.goBack()}/>

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
    },
    text:{
        fontSize:20,
        marginLeft:12

    }


})


export default MemberRegisterScreen;