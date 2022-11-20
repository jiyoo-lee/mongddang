import { Navigate } from "react-router-dom";
import axios from "axios";

const DeleteAxios = (url, requestData, callback) => axios.create({
    baseURL: 'http://ec2-52-79-38-165.ap-northeast-2.compute.amazonaws.com/api/v1',
    headers: {
      "Content-Type": "application/json",
      "Authorization": sessionStorage.getItem("token")
    }})
  .delete(url, requestData)
  .then(response => {
    if (response.data.success) {
      callback(response.data);
    }
    else {
      alert(response.data.data);
       if(response.data.data === "[WARNING] TOKEN IS NOT VALIDATE") {
        sessionStorage.removeItem("token");
        return <Navigate to={'/login'}/>
      }
    }
  })
  .catch(error=>{
    alert(error);
  });


export default DeleteAxios;