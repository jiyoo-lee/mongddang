import axios from "axios";

export const PostAxios = (url, requestData, callback) => {

  return axios.create({
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
      }
    })
    .catch(error=>{
      alert(error);
    });
};
  
