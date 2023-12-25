import { View, FlatList, TouchableOpacity, Text, Image, ActivityIndicator} from 'react-native';
import React, { useState, useEffect } from 'react';
import { StyleSheet } from 'react-native';
import Heart from 'react-heart';


// Example API endpoint
const apiUrl = 'https://dummyjson.com/products';

const HomeScreen = ({ navigation }) => {
  
  const [data, setData]       = useState([]);
  const [loading, setLoading] = useState(true);


   const OnAddCart = () => {
    console.log('--cart--');
  }
  const OnAddFav = () => {
    console.log('--fav--');
  };

  //Use Effect hook
  //This will be called after the 
  //Rendering happens.
   useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(apiUrl);
        if (!response.ok) {
          throw new Error('Network response was not ok.');
        }
        const jsonData = await response.json();
        console.log("Data Dump --> ",jsonData);
        setData(jsonData); // Set the fetched data in the state
        setLoading(false); // Update loading state to false

        
      } catch (error) {
        console.error('There was a problem fetching the data:', error);
        setLoading(false); // Update loading state to false even if there's an error
      }
    };

     fetchData();

      // Cleanup function if needed (componentWillUnmount equivalent)
    return () => {
      // Any cleanup code
    };
   }, []);
 

  // const renderItem = ({ item }) => (
  //   <TouchableOpacity onPress={() => navigation.navigate('ProductDetails', { productId: item.id })}>
  //     <View>
  //       <Image source={{ uri: item.thumbnail }} style={{ width: 100, height: 100 }} />
  //       <Text>{item.title}</Text>
  //       <Text>{item.price}</Text>
  //       {/* Add to cart and favorite functionality */}
  //       <TouchableOpacity onPress={() => OnAddCart }>
  //         <Text>Add to Cart</Text>
  //       </TouchableOpacity>
  //       <TouchableOpacity onPress={() => OnAddFav}>
  //         <Text>Add to Favorites</Text>
  //       </TouchableOpacity>
  //     </View>
  //   </TouchableOpacity>
  // );

   // Render a loading indicator if data is still being fetched
  if (loading) {
    return (
      <View style={{ flex: 1, justifyContent: 'center', alignItems: 'center' }}>
        <ActivityIndicator size="large" color="#0000ff" />
      </View>
    );
  }

  const styles = StyleSheet.create({
    container: {
      flex: 0,
      justifyContent: 'center',
      alignItems: 'center',
    },
    recommendedText: {
      fontSize: 28, // Adjust the font size as needed
      // You can also set other styles here, if required
    },
  });


  console.log("Data Length",data.length);
  // Render the fetched data in the UI

  return (
      <View style={{ flex: 1, alignItems: 'left', justifyContent: 'center',alignSelf: 'center' }}>
        <View style={{ flex: 0, alignItems: 'left', justifyContent: 'center', height:210 }}>
          <FlatList
            horizontal
            contentContainerStyle={{flexDirection : "row", flexWrap : "wrap", justifyContent: 'center'}}
            data={data.products} // Pass the fetched data to FlatList component
            // keyExtractor={({index})=>index?.toString()} // Provide a unique key for each item
            ItemSeparatorComponent={() => <View style={{height: 30,width:30}} />}
            keyExtractor={(item, index) => {
              return item.id;
            }}
            renderItem={({ item,index }) => {
              return (
                <View 
                  style={{borderWidth:1,borderColor:"#000000",width:200,height:200,justifyContent: "center", alignItems: "center",padding:25,backgroundColor:'#F8F9FB'}}>              
                      <View style={{width: 80, height:80}} >
                          <Image
                            borderRadius={15}
                            style= {{flex:1 , width: undefined, height: undefined,padding:30}}
                            source={{
                              uri: item.thumbnail,
                            }}
                            defaultSource={loading}
                          />
                      </View>
                      <View style={{width: 80, height:50}}/>
                      

                      {/* <TouchableOpacity
                          style={{
                            borderWidth: 1,
                            borderColor: 'rgba(0,0,0,0.2)',
                            alignItems: 'center',
                            justifyContent: 'center',
                            width: 30,
                            position: 'absolute',
                            bottom: 10,
                            right: 10,
                            height: 30,
                            backgroundColor: '#2A4BA0',
                            borderRadius: 30,
                          }}
                        >
                        <Text style={{ color:"white",fontSize: 20}}>+</Text>
                    </TouchableOpacity> */}
                  <Text style={{backgroundColor: 'transparent', alignSelf: 'flex-start',fontWeight: "bold",color:"black"}}>${item.price}</Text>
                  <Text numberOfLines={1} style={{alignSelf: 'flex-start'}} >{item.title}</Text>             
                </View>
              )
            }}
          />
        </View>
      </View>
   );
};

export default HomeScreen;
