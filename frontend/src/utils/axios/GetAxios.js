import axios from "axios";
import { useNavigate } from "react-router-dom";

const navigate = useNavigate;

const GetAxios = (url, requestData, callback) => axios.create({
    baseURL: 'http://ec2-52-79-38-165.ap-northeast-2.compute.amazonaws.com/api/v1',
   //baseURL: 'http://localhost:8080/api/v1',
    headers: {
      "Authorization": sessionStorage.getItem("token")
    }})
  .get(url, requestData)
  .then(response => {
    if (response.data.success) {
        callback(response.data);
    }
    else {
      if(response.data.data === "세션이 만료되었습니다. 다시 로그인해주세요.") {
        console.log("may i?");
        sessionStorage.removeItem("token");
        navigate("/login");
      }
      else {
        console.log(response.data.data);
        alert(response.data.data);
      }
    }
  })
  .catch(error=>{
    console.log(error);
  });

export default GetAxios;