import {createBottomTabNavigator} from "@react-navigation/bottom-tabs";
import {StyleSheet, Text, View} from "react-native";
import {NavigationContainer} from "@react-navigation/native";


const Tab = createBottomTabNavigator();

const Home = () => {
  return (
      <View style={styles.container}>
        <Text >home page</Text>
      </View>
  );
};

const Life = () => {
  return (
      <View>
        <Text>동네 생활 페이지</Text>
      </View>
  );
};

const ItemRegisterPage = () =>{
  return (
      <View >
        <Text>chatting page</Text>
      </View>
  );
};

const MyPage = () =>{
  return (
      <View>
        <Text>My page</Text>
      </View>
  );
};

const MyTabs = () =>{

  return (
      <NavigationContainer>
      <Tab.Navigator>
        <Tab.Screen name="홈" component={Home}/>
        <Tab.Screen name="동네생활" component={Life}/>
        <Tab.Screen name="아이템 등록 페이지" component={ItemRegisterPage}/>
        <Tab.Screen name="나의 당근이지" component={MyPage}/>
      </Tab.Navigator>
      </NavigationContainer>
  );
};

const styles = StyleSheet.create({
  container:{
    flex:1,
    backgroundColor:"#708090"
  }
})

export default MyTabs;

