import getAxiosFn from "../../../utils/axios/getAxiosFn";
import { useState } from "react";
import MyButton from "../../button/MyButton";
import axios from "axios";
import PostAxios from "../../../utils/axios/PostAxios";
import { useLocation } from "react-router-dom";

const MyPainting = () => {

    const [option, setOption] = useState([]);

    const location = useLocation();

    const dropsId = location.state.dropsId;
    const genre = location.state.options;


    const onDrop = async(file) => { 
        let formData = new FormData();
        formData.append("api_key", "516516722966632");
        formData.append("upload_preset", "vspuoidk");
        formData.append("timestamp", (Date.now() / 1000) | 0);
        formData.append(`file`, file);

        console.log(formData);

        const config = {
            header: { "Content-Type": "multipart/form-data" }
        }

        await axios.post('https://api.cloudinary.com/v1_1/dlcpn7mul/image/upload', formData, config)
        .then(res=>{
            console.log(res);

            const requestBody = {
                memberId: sessionStorage.getItem("userId"),
                dropsId: 4,
                genreId: 1,
                name: "테스트 내용",
                paintingUrl: res.data.url,
                description: ""
            };

            PostAxios('/painting', requestBody, (res)=>{
                console.log(res);
            });
        })
    }

    return (
        <>
        <div className="upload_drops">
            <span className="home_message"> 그림을 업로드 해주세요 </span>
        <br/>
        <br/>
        장르 별 <select className="select_box">
        {genre.map(op=>(
                <option key={op.id} value={op.id}>{op.name}</option>
            ))}
        </select>
        <br/>
        <br/>
            <img className="drops" src={process.env.PUBLIC_URL + `../.././img/default_drops.png`}/>
            <input type='file' name="imgUpload" onChange={(e)=>onDrop(e.target.files[0])} accept="image/*"/>
            <MyButton text={'업로드'}/>
        </div>
        </>
    );
}

export default MyPainting;