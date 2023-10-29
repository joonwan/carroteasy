import {useState} from "react";
import {Button, StyleSheet, Text, TextInput, View} from "react-native";
import axios from "axios";

const MemberRegisterScreen = ({navigation}) => {

    const [loginId, setLoginId] = useState(null);
    const [password, setPassword] = useState(null);
    const [name, setName] = useState(null);
    const [city, setCity] = useState(null);
    const [goo, setGoo] = useState(null);
    const [dong, setDong] = useState(null);


    return (
        <View style={styles.container}>

            <Text style={styles.text}>
                ID
            </Text>

            <TextInput
                autoCapitalize="none"
                style={styles.input}
                placeholder="아이디를 입력해주세요"
                onChangeText={setLoginId}
                value={loginId}
            />

            <Text style={styles.text}>
                Password
            </Text>

            <TextInput
                autoCapitalize="none"
                style={styles.input}
                placeholder="패스워드를 입력해 주세요"
                onChangeText={setPassword}
                value={password}
            />

            <Text style={styles.text}>
                Name
            </Text>

            <TextInput
                autoCapitalize="none"
                style={styles.input}
                placeholder="이름을 입력해주세요"
                onChangeText={setName}
                value={name}
            />

            <Text style={styles.text}>
                City
            </Text>

            <TextInput
                autoCapitalize="none"
                style={styles.input}
                placeholder="도시를 입력해주세요"
                onChangeText={setCity}
                value={city}
            />

            <Text style={styles.text}>
                Goo
            </Text>

            <TextInput
                autoCapitalize="none"
                style={styles.input}
                placeholder="구를 입력해주세요"
                onChangeText={setGoo}
                value={goo}
            />

            <Text style={styles.text}>
                Dong
            </Text>

            <TextInput
                autoCapitalize="none"
                style={styles.input}
                placeholder="동을 입력해주세요"
                onChangeText={setDong}
                value={dong}
            />

            <Button title="회원 가입" onPress={
                () => {
                    const data = {
                        loginId, password, name, city, goo, dong
                    }

                    axios.post(
                        "http://localhost:8080/members/new",
                        JSON.stringify(data),
                        {
                            headers: {
                                'Content-Type': 'application/json'
                            }
                        }
                    ).then(navigation.navigate("LoginScreen"))
                        .catch(err => console.log(err));
                }}
            />
            <Button title="이전" onPress={() => navigation.goBack()}/>

        </View>
    )
}
const styles = StyleSheet.create({

    container: {
        marginVertical: 100,
        alignContent: "center"
    },
    input: {
        height: 40,
        margin: 12,
        borderWidth: 1,
    },
    buttonBox: {
        flexDirection: "row",
        justifyContent: "center"
    },
    text: {
        fontSize: 20,
        marginLeft: 12

    }


})


export default MemberRegisterScreen;