import { Navigate } from "react-router-dom";
import axios from "axios";

const PostAxios = (url, requestData, callback) => axios.create({
    baseURL: 'http://localhost:8080/api/v1',
    headers: {
      "Content-Type": "application/json",
      "Authorization": sessionStorage.getItem("token")
    }})
  .post(url, requestData)
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


export default PostAxios;