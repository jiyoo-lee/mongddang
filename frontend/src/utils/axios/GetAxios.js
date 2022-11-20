import axios from "axios";
import { Navigate } from "react-router-dom";

const GetAxios = (url, requestData, callback) => axios.create({
    baseURL: 'http://ec2-52-79-38-165.ap-northeast-2.compute.amazonaws.com/api/v1',
    headers: {
      "Authorization": sessionStorage.getItem("token")
    }})
  .get(url, requestData)
  .then(response => {
    if (response.data.success) {
        callback(response.data);
    }
    else {
      if(response.data.data === "[WARNING] TOKEN IS NOT VALIDATE") {
        console.log("may i?");
        sessionStorage.removeItem("token");
        <Navigate to="/login"/>
      }
      alert(response.data.data);
    }
  })
  .catch(error=>{
    console.log(error);
  });

export default GetAxios;