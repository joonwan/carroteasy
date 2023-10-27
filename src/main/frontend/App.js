import {createBottomTabNavigator} from "@react-navigation/bottom-tabs";
import {NavigationContainer} from "@react-navigation/native";
import {createNativeStackNavigator} from "@react-navigation/native-stack";
import LoginScreen from "./LoginScreen";
import LifeScreen from "./LifeScreen";
import MemberRegisterScreen from "./MemberRegisterScreen";
import MyPage from "./MyInfoScreen";
import ItemRegisterPage from "./ItemRegisterScreen";
import Home from "./HomeScreen";
import ItemDetailScreen from "./ItemDetailScreen";


const Tab = createBottomTabNavigator();
const Stack = createNativeStackNavigator();

const Base = () => {
    return (
        <NavigationContainer>
            <Stack.Navigator>
                <Stack.Screen name="LoginScreen" component={LoginScreen} options={{headerShown: false}}/>
                <Stack.Screen name="MyTabs" component={MyTabs} options={{headerShown:false}}/>
                <Stack.Screen name="MemberRegisterScreen" component={MemberRegisterScreen} options={{headerShown:false}}/>
                <Stack.Screen name="ItemDetailScreen" component={ItemDetailScreen} />
            </Stack.Navigator>
        </NavigationContainer>
    );
}


const MyTabs = () => {

    const Stack = createNativeStackNavigator();

    return (

        <Tab.Navigator>
            <Tab.Screen name="홈" component={Home}/>
            <Tab.Screen name="동네생활" component={LifeScreen}/>
            <Tab.Screen name="아이템 등록 페이지" component={ItemRegisterPage}/>
            <Tab.Screen name="나의 당근이지" component={MyPage}/>
        </Tab.Navigator>


    );
};


export default Base;

