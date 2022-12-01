import axios from "axios";
import { Navigate } from "react-router-dom";

const PutAxios = (url, requestData, callback) => axios.create({
    //baseURL: 'http://ec2-52-79-38-165.ap-northeast-2.compute.amazonaws.com/api/v1',
    baseURL: 'http://localhost:8080/api/v1',
    headers: {
      "Content-Type": "application/json",
      "Authorization": sessionStorage.getItem("token")
    }})
  .put(url, requestData)
  .then(response => {
    console.log(response);
    if (response.data.success) {
      callback(response.data);
    }
    else {
      alert(response.data.data);
      if(response.data.data === "[WARNING] TOKEN IS NOT VALIDATE") {
        sessionStorage.removeItem("token");
        <Navigate to="/login"/>
      }
    }
  })
  .catch(error=>{
    alert(error);
  });

export default PutAxios;