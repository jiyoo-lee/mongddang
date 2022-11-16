import { useRef, useState } from "react";
import MyButton from "../button/MyButton";
import { useLocation } from "react-router-dom";
import PutAxios from "../../utils/axios/PutAxios";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const JoinProfile = () => {

    const navigate = useNavigate();

    //useRef를 이용해 input 태그 접근
    const imageInput = useRef();
    const [image, setImage] = useState("");
    const [profile, setProfile] = useState("");

    const userId = sessionStorage.getItem("userId")

    const saveFileImage = (e) => {
        setImage(URL.createObjectURL(e.target.files[0]));
        setProfile(e.target.files[0]);
    }

    const onClickImageUpload = () => {
        imageInput.current.click();
    }


    const onDrop = async() => { 
        let formData = new FormData();
        formData.append("api_key", "516516722966632");
        formData.append("upload_preset", "vspuoidk");
        formData.append("timestamp", (Date.now() / 1000) | 0);
        formData.append(`file`, profile);

        const config = {
            header: { "Content-Type": "multipart/form-data" }
        }

        await axios.post('https://api.cloudinary.com/v1_1/dlcpn7mul/image/upload', formData, config)
        .then(res => { 
            const requestBody = {
                profileUrl : res.data.url,
            }
            
            PutAxios('/member/'+userId+'/profile-picture',requestBody, (res)=>{
                alert("저장되었습니다.");
                navigate('/home/feed');
            });

            }
        );
    }


 return (
    <div className="member_wrapper">
        <div className='member_form'>
            <span style={{"fontSize":18}}> <b>프로필 사진으로 자신을 표현해보세요</b> </span>
        <br/>
        <br/>

            <div className ="image_wrapper">
                {image && (
                    <img alt="sample"
                    className="profile"
                    src={image}
                    style= {{ margin:"auto"}} />
                )}
            </div>
            <input type='file' name="imgUpload" style={{display:"none"}} ref={imageInput} onChange={saveFileImage} accept="image/*"/>
            <br/>
           <b>{userId}</b>
        <br/>
        <br/>
            <MyButton onClick={onClickImageUpload} text={'이미지 업로드'}/>
        <br/>
        <br/>
            <MyButton type={'positive'} text={'프로필 저장'} onClick={onDrop}/>
        </div>
    </div>
 );
}

export default JoinProfile;