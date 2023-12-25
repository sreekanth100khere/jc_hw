import React from 'react';
import {View, Text, Image, TouchableOpacity} from 'react-native';

const ProductDetailsScreen = ({route, navigation}) => {
  const {productId} = route.params;
  // Assume you have product details based on productId
  // Example: const product = getProductDetails(productId);
  const OnAddCart = () => {
    console.log('--cart--');
  }
  const OnAddFav = () => {
    console.log('--fav--');
  };
  return (
    <View>
      {/* Product details, images, price, description */}
      <TouchableOpacity style={styles.button} onPress={OnAddCart}>
        <Text>Add to Cart</Text>
      </TouchableOpacity>
      <TouchableOpacity style={styles.button} onPress={OnAddFav}>
        <Text>Add to Favorites</Text>
      </TouchableOpacity>
      {/* Back button */}
      <TouchableOpacity onPress={() => navigation.goBack()}>
        <Text>Back</Text>
      </TouchableOpacity>
    </View>
  );
};

export default ProductDetailsScreen;
