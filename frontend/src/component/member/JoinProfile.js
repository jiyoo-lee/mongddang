import { useRef, useState } from "react";
import MyButton from "../button/MyButton";
import { useLocation, useNavigate } from "react-router-dom";
import PutAxios from "../../utils/PutAxios";

const JoinProfile = () => {

    //useRef를 이용해 input 태그 접근
    const imageInput = useRef();
    const [image, setImage] = useState("");
    const [extension, setExtension] =useState("");
    const location = useLocation();
    const navigate = useNavigate();

    const userId = location.state.userId;
    const saveFileImage = (e) => {
        const temp = (e.target.files[0].name).split(".");
        setExtension(temp[temp.length-1]);
        setImage(URL.createObjectURL(e.target.files[0]));
    }

    const onClickImageUpload = () => {
        imageInput.current.click();
    }

    const profileUploadUrl = '/member/'+userId+'/profile-picture';

    const requestBody = {
        extension: extension
    }

    const onClick = () => {
        console.log(profileUploadUrl);
        console.log(requestBody);
        console.log(sessionStorage.getItem("token"));

        PutAxios(profileUploadUrl, requestBody, (e)=>{
            console.log(e)
        })
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
            <MyButton type={'positive'} text={'몽땅 시작하기'} onClick={onClick}/>
        </div>
    </div>
 );
}

export default JoinProfile;