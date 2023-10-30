import {Button, SafeAreaView, ScrollView, Text, View, StyleSheet, TouchableOpacity} from "react-native";
import {useEffect, useState} from "react";
import axios from "axios";
import AsyncStorage from "@react-native-async-storage/async-storage";

const getMemberName = async () =>{
    try{
        const memberName = await AsyncStorage.getItem("memberName");
        if(memberName !== null){
            return memberName;
        }
    }catch(e){
        console.log(e);
    }
}

const MyPage = () =>{
    const [memberName, setMemberName] = useState("");
    useEffect( ()=>{
        const setting = async () =>{
            const value = await getMemberName();
            setMemberName(value);
        }
        setting();
    },[])
    return (
        <SafeAreaView>
            <ScrollView>
                <View style={styles.memberSection}>
                    <Text style={styles.memberName}>{memberName}</Text>
                    <Button title="프로필 보기"/>
                </View>

                <View style={styles.myTransactionSection}>
                    <Text style={styles.title}>나의 거래</Text>
                    <TouchableOpacity style={styles.touchable}>
                        <Text style={styles.text}>관심목록</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.touchable}>
                        <Text style={styles.text}>판매내역</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.touchable}>
                        <Text style={styles.text}>구매내역</Text>
                    </TouchableOpacity>
                </View>

                <View style={styles.myTransactionSection}>
                    <Text style={styles.title}>나의 동네생활</Text>
                    <TouchableOpacity style={styles.touchable}>
                        <Text style={styles.text}>동네생활 활동</Text>
                    </TouchableOpacity>
                </View>

                <View style={styles.myTransactionSection}>
                    <Text style={styles.title}>나의 비즈니스</Text>
                    <TouchableOpacity style={styles.touchable}>
                        <Text style={styles.text}>비즈니스 프로필 관리</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.touchable}>
                        <Text style={styles.text}>광고</Text>
                    </TouchableOpacity>
                </View>

                <View style={styles.myTransactionSection}>
                    <Text style={styles.title}>기타</Text>
                    <TouchableOpacity style={styles.touchable}>
                        <Text style={styles.text}>내 동네 설정</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.touchable}>
                        <Text style={styles.text}>동네 인증하기</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.touchable}>
                        <Text style={styles.text}>중고 카테고리 설정</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.touchable}>
                        <Text style={styles.text}>알림 키워드 설정</Text>
                    </TouchableOpacity>
                </View>

                <View style={styles.myTransactionSection}>
                    <Text style={styles.title}>소식 및 지원</Text>
                    <TouchableOpacity style={styles.touchable}>
                        <Text style={styles.text}>자주 묻는 질문</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.touchable}>
                        <Text style={styles.text}>공지사항</Text>
                    </TouchableOpacity>
                    <TouchableOpacity style={styles.touchable}>
                        <Text style={styles.text}>약관 및 정책</Text>
                    </TouchableOpacity>
                </View>
            </ScrollView>
        </SafeAreaView>
    );
};

const styles = StyleSheet.create({
    memberSection:{
        flexDirection : "row",
        margin :10,
        padding:10,
        justifyContent :"space-between",
        alignItems:"center",
        borderBottomWidth:1
    },
    memberName :{
        fontWeight:"bold",
        fontSize:20
    },
    myTransactionSection:{
        margin:10,
        padding:10,
        borderBottomWidth:1
    },
    touchable:{
        marginVertical:10
    },
    text :{
        fontWeight:"bold",
        fontSize:15
    },
    title :{
        fontWeight:"bold",
        fontSize:20,
        marginVertical:10
    }
})

export default MyPage;