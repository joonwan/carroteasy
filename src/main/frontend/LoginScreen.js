import {useState} from "react";
import {Button, TextInput, View, Text, StyleSheet} from "react-native";

const LoginScreen = ({navigation}) => {

    const [loginId, setLoginId] = useState(null);
    const [password, setPassword] = useState(null);
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
                            console.log("hello login");
                            navigation.navigate("MyTabs");
                        }
                    }
                />

                <Button
                    title="회원 가입"
                    onPress={
                        () => {
                            console.log("hello login");
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