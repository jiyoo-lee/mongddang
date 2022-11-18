import { useRef, useState } from "react";
import MyButton from "../../button/MyButton";
import axios from "axios";
import PostAxios from "../../../utils/axios/PostAxios";
import { useLocation, useNavigate } from "react-router-dom";

const MyPainting = () => {

    const navigate = useNavigate();

    //useRef를 이용해 input 태그에 접근
    const imageInput = useRef();
    const [prevImage, setPrevImage] = useState("");
    const [image, setImage] = useState("");


    const location = useLocation();

    const dropsId = location.state.dropsId;
    const genre = location.state.options;

    const [inputs, setInputs] = useState({
        name : '',
        genreId : 1,
        content : '',
      });
  
      const {name, genreId, content} = inputs
  
      const onChange = (e) => {
          const {name, value} = e.target;
          setInputs({
              ...inputs,
              [name] : value
          });
      };


    const saveFileImage = (e) => {
        setPrevImage(URL.createObjectURL(e.target.files[0]));
        setImage(e.target.files[0]);
    }

    const onClickImageUpload = () => {
        imageInput.current.click();
    }


    const onDrop = async() => { 
        let formData = new FormData();
        formData.append("api_key", "516516722966632");
        formData.append("upload_preset", "vspuoidk");
        formData.append("timestamp", (Date.now() / 1000) | 0);
        formData.append(`file`, image);

        console.log(formData);

        const config = {
            header: { "Content-Type": "multipart/form-data" }
        }

        await axios.post('https://api.cloudinary.com/v1_1/dlcpn7mul/image/upload', formData, config)
        .then(res=>{

            const requestBody = {
                memberId: sessionStorage.getItem("userId"),
                dropsId: dropsId,
                genreId: genreId,
                name: name,
                paintingUrl: res.data.url,
                description: content,
            };

            PostAxios('/painting', requestBody, (res)=>{
                navigate('/drawer')
            });
        })
    }

   
    return (
        <>
        <div className="upload_drops">
            <span className="home_message"> 그림을 업로드 해주세요 </span>
        <br/>
        <br/>
        장르 별 <select className="select_box" name="genreId" onChange={onChange}> 
        {genre.map(op=>(
                <option key={op.id} value={op.id}>{op.name}</option>
            ))}
        </select>
        <br/>
        <br/>
            <input className="member_text" type='text' placeholder="제목을 입력해주세요" name="name" onChange={onChange}/>
        <br/>
        <div className ="image_wrapper">
                {prevImage && (
                    <img alt="painting"
                    className="painting"
                    src={prevImage}
                    style= {{ margin:"auto"}} />
                )}
            </div>
       
        <br/>
        <br/>
            <textarea name="content" onChange={onChange}></textarea>
        <br/>
            <input type='file' name="imgUpload" style={{display:"none"}} ref={imageInput} onChange={saveFileImage} accept="image/*"/>
            <MyButton text={'이미지 업로드'} onClick={onClickImageUpload}/>
            <MyButton text={'완료'} onClick={onDrop}/>
        </div>
        </>
    );
}

export default MyPainting;