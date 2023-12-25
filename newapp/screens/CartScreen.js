import React from 'react';
import { View, Text, FlatList, TouchableOpacity } from 'react-native';

const CartScreen = ({ navigation }) => {
  // Assume you have cart items and quantities
  const cartItems = [
    // Sample cart data
    // Assume you have the cart data here
  ];
   const OnAddCart = () => {
    console.log('--cart--');
  }
  
  const renderItem = ({ item }) => (
    <View>
      <Text>{item.title}</Text>
      <Text>{item.price}</Text>
      {/* Quantity adjustment */}
      {/* Transition animation on item removal */}
       <TouchableOpacity onPress={OnAddCart}>
        <Text>Remove</Text>
      </TouchableOpacity> 
    </View>
  );

  return (
    <View>
      <FlatList
        data={cartItems}
        renderItem={renderItem}
        keyExtractor={(item) => item.id.toString()}
      />
      {/* Total amount */}
    </View>
  );
};

export default CartScreen;

